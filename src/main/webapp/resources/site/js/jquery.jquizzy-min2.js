//eval
(function($) {
    $.fn.jquizzy = function(settings) {
        var defaults = {
            questions: null,
            twitterStatus: 'I scored {score}% on this awesome! Check it out!',
            startText: '开始',
            endText: '牙龈健康报告',
            splashImage: 'img/start.png',
            twitterImage: 'img/share.png',
            twitterUsername: 'jQuizzy',
            resultComments: {
                perfect: 'Perfect!',
                excellent: 'Excellent!',
                good: 'Good!',
                average: 'Acceptable!',
                bad: 'Disappointing!',
                poor: 'Poor!',
                worst: 'Nada!'
            }
        };
        var config = $.extend(defaults, settings);
        if (config.questions === null) {
            $(this).html('<div class="intro-container slide-container"><h2 class="qTitle">Failed to parse questions.</h2></div>');
            return
        }
        var superContainer = $(this),
        answers = [],
        introFob = '<div class="intro-container slide-container"><a class="nav-start" href="#"><img src="' + config.splashImage + '" /></a><div ><a class="submit" href="javascript:void(0)" style="color: #FFFFFF;">开&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;始</a></div></div>',
        exitFob = '<div class="results-container slide-container"><div class="question-number">' + config.endText + '</div><div class="result-keeper"></div></div><div class="notice">请选择一个选项！</div><div class="progress-keeper" ><div class="progress"></div></div>',
        contentFob = '';
        superContainer.addClass('main-quiz-holder');
        for (questionsIteratorIndex = 0; questionsIteratorIndex < config.questions.length; questionsIteratorIndex++) {
            contentFob += '<div class="slide-container"><div class="question-number">' + (questionsIteratorIndex + 1) + '/' + config.questions.length + '</div><div class="question">' + config.questions[questionsIteratorIndex].question + '</div><ul class="answers">';
            for (answersIteratorIndex = 0; answersIteratorIndex < config.questions[questionsIteratorIndex].answers.length; answersIteratorIndex++) {
                contentFob += '<li>' + config.questions[questionsIteratorIndex].answers[answersIteratorIndex] + '</li>'
            }
            contentFob += '</ul><div class="nav-container">';
            if (questionsIteratorIndex !== 0) {
                contentFob += '<div class="prev"><a class="nav-previous" href="#">上一题</a></div>'
            }
            if (questionsIteratorIndex < config.questions.length - 1) {
                contentFob += '<div class="next"><a class="nav-next" href="#">下一题</a></div>'
            } else {
                contentFob += '<div class="next final"><a class="nav-show-result" href="#">点击查看结果</a></div>'
            }
            contentFob += '</div></div>';
            answers.push(config.questions[questionsIteratorIndex].correctAnswer)
        }
        superContainer.html(introFob + contentFob + exitFob);
        var progress = superContainer.find('.progress'),
        progressKeeper = superContainer.find('.progress-keeper'),
        notice = superContainer.find('.notice'),
        progressWidth = progressKeeper.width(),
        userAnswers = [],
        questionLength = config.questions.length,
        slidesList = superContainer.find('.slide-container');
        function checkAnswers() {
            var resultArr = [],
            flag = false;
            for (i = 0; i < answers.length; i++) {
                if (answers[i] == userAnswers[i]) {
                    flag = true
                } else {
                    flag = false
                }
                resultArr.push(flag)
            }
            return resultArr
        }
        function roundReloaded(num, dec) {
            var result = Math.round(num * Math.pow(10, dec)) / Math.pow(10, dec);
            return result
        }
		
        function judgeSkills(userAnswers) {
			//var answersString = userAnswers.join('');
            if (userAnswers==1||userAnswers==2||userAnswers==3||userAnswers==4||userAnswers==5||userAnswers==6) 
			    return config.resultComments.advice1;
            else if (userAnswers==7||userAnswers==8) return config.resultComments.advice2;
            else if (userAnswers==9) return config.resultComments.advice3;
            else if (userAnswers==10) return config.resultComments.advice4;
            else if (userAnswers==11||userAnswers==12) return config.resultComments.advice5;
        }
        progressKeeper.hide();
        notice.hide();
        slidesList.hide().first().fadeIn(500);
        superContainer.find('li').click(function() {
            var thisLi = $(this);
            if (thisLi.hasClass('selected')) {
                thisLi.removeClass('selected')
            } else {
                thisLi.parents('.answers').children('li').removeClass('selected');
                thisLi.addClass('selected')
            }
        });
        superContainer.find('.nav-start').click(function() {
            $(this).parents('.slide-container').fadeOut(500,
            function() {
                $(this).next().fadeIn(500);
                progressKeeper.fadeIn(500)
            });
            return false
        });
		superContainer.find('.submit').click(function() {
            $(this).parents('.slide-container').fadeOut(500,
            function() {
                $(this).next().fadeIn(500);
                progressKeeper.fadeIn(500)
            });
            return false
        });
        superContainer.find('.next').click(function() {
            if ($(this).parents('.slide-container').find('li.selected').length === 0) {
                notice.fadeIn(300);
                return false
            }
            notice.hide();
            $(this).parents('.slide-container').fadeOut(500,
            function() {
                $(this).next().fadeIn(500)
            });
            progress.animate({
                width: progress.width() + Math.round(progressWidth / questionLength)
            },
            500);
            return false
        });
        superContainer.find('.prev').click(function() {
            notice.hide();
            $(this).parents('.slide-container').fadeOut(500,
            function() {
                $(this).prev().fadeIn(500)
            });
            progress.animate({
                width: progress.width() - Math.round(progressWidth / questionLength)
            },
            500);
            return false
        });
        superContainer.find('.final').click(function() {
            if ($(this).parents('.slide-container').find('li.selected').length === 0) {
                notice.fadeIn(300);
                return false
            }
            superContainer.find('li.selected').each(function(index) {
                userAnswers.push($(this).parents('.answers').children('li').index($(this).parents('.answers').find('li.selected')) + 1)
            });
            progressKeeper.hide();      
            resultSet = '<h2 class="qTitle">' + judgeSkills(userAnswers) + '<div class="jquizzy-clear"></div>';
            superContainer.find('.result-keeper').html(resultSet).show(500);
            return false
        })
    }
})(jQuery);